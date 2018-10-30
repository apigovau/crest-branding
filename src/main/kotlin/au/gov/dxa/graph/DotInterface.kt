package au.gov.dxa.graph

class DotInterface(interfaceDef:String, iterator:Iterator<String> ) {

    /*

    interface DefinitionsApi {
	 + <i>null(String domain,String id)</i><b>:Definition</b>
    }

     */

    var name:String = ""
    var memebrs = mutableMapOf<String,String>()
    var longestMember = 0

    init{
        name = interfaceDef.removePrefix("interface ").removeSuffix(" {").replace(" ","")
        while(iterator.hasNext()){
            val next = iterator.next()
            if(next.startsWith("\t + ")){
                var memberParts = next.replace("<i>","").replace("</i>","").replace("<b>","").replace("</b>","").split(":")
                //println(memberParts)
                var memberName = memberParts[0].removePrefix("\t + ").removeSuffix(" <b>")
                var memberType = memberParts[1].split("<")[0]
                memebrs.put(memberName, memberType)

                val combined = "${memberName}:${memberType}"
                longestMember = maxOf(longestMember, combined.length)

            }
            if(next == "}") break
        }
    }

    override fun toString():String{
        return name + memebrs
    }

}