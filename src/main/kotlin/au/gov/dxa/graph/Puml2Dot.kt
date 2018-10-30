package au.gov.dxa.graph

class Puml2Dot(var puml:String) {

    val classes = mutableListOf<DotClass>()
    val interfaces = mutableListOf<DotInterface>()
    val relations = mutableListOf<String>()

    init{
        val pumlIterator = puml.replace("\r","").split("\n").iterator()

        while(pumlIterator.hasNext()){
            val line = pumlIterator.next()
            if(line.startsWith("class ")){
                classes.add(DotClass(line, pumlIterator))
            }
            if(line.startsWith("interface ")){
                val dotInteface = DotInterface(line, pumlIterator)

                if(interfaces.filter { it.name == dotInteface.name }.isEmpty()) {
                    interfaces.add(dotInteface)
                } else{
                    val existingInterface = interfaces.filter { it.name == dotInteface.name }.first()
                    existingInterface.memebrs.putAll(dotInteface.memebrs)
                    existingInterface.longestMember = maxOf(existingInterface.longestMember, dotInteface.longestMember)
                }
            }

            if(line.contains("-->"))relations.add('"' + line.replace("-->","\"->\"").replace(" ","") + '"')
            if(line.contains("*--"))relations.add('"' + line.replace("*--","\"->\"").replace(" ","") + "\" [arrowtail=diamond, dir=back]")
        }
    }

    fun dot():String{
        val head = """
digraph G {

fontname = "Bitstream Vera Sans";

node [
fontname = "Bitstream Vera Sans"
fontsize = 10
shape="none"
]

edge [
arrowsize=0.8
minlen=1
fontname = "Courier"
fontsize="8"
]

nodesep=0.486111;
ranksep=0.833333;
remincross=true;
searchsize=500;
"""

        var output = ""

        for(dotInterface in interfaces){
            var interfaceStr = """"${dotInterface.name}"[label=<<font face="Courier"><table style="rounded" border="6" color="white" cellspacing="0">"""
            interfaceStr += """<tr><td port="port1" border="1"  bgcolor="#ED6A5A" color="#ED6A5A"><font point-size="14" color="white">${dotInterface.name} </font></td></tr>"""
            interfaceStr += """<tr><td port="port3" border="1" bgcolor="#dddddd" color="#dddddd" >"""
            for(member in dotInterface.memebrs){
                interfaceStr += """<font color="blue">	&#x25CB; </font>${member.key}    <b>: ${member.value}${" ".repeat(dotInterface.longestMember - "${member.key}:${member.value}".length)}</b><br/>"""
            }
            interfaceStr += """</td></tr>"""
            interfaceStr += "</table></font>>];"

            output += "\n${interfaceStr}\n"
        }

        for(dotClass in classes){
            var classStr = """"${dotClass.name}"[label=<<font face="Courier"><table style="rounded" border="6" color="white" cellspacing="0">"""
            classStr += """<tr><td port="port1" border="1"  bgcolor="#68AFCB" color="#68AFCB"><font point-size="14" color="white">${dotClass.name} </font></td></tr>"""
            classStr += """<tr > <td port = "port2" border ="1"  bgcolor="#dddddd" color="#dddddd">"""
            for(member in dotClass.memebrs) {
                classStr += """<font color="#8B0000"> &#x25A1; </font>${member.key} : <b>${member.value} </b>${" ".repeat(dotClass.longestMember - "${member.key}:${member.value}".length)}<br/>"""
            }
            classStr+="""</td></tr>"""
            classStr += """</table></font>>];"""

            output += "\n${classStr}\n"
        }

        output += relations.joinToString("\n")

        return  head +  output + "\n}"
    }

}