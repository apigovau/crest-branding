package au.gov.dxa.graph

class DotClass(classDef:String, iterator:Iterator<String> ) {

    var name:String = ""
    var memebrs = mutableMapOf<String,String>()

    var longestMember = 0

    init{
        name = classDef.removePrefix("class  ").removeSuffix(" {").replace(" ","")
        while(iterator.hasNext()){
            val next = iterator.next()
            if(next.startsWith("\t - ")){
                var memberParts = next.split(":")
                var memberName = memberParts[0].removePrefix("\t - ").removeSuffix(" <b>")
                var memberType = memberParts[1].removeSuffix("</b>")
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