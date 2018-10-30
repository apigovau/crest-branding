package au.gov.dxa.graph

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.net.URL
import java.security.MessageDigest


@RestController
class Controller {
/*
    @CrossOrigin
    @RequestMapping("/swagger.svg")
    fun swagger(@RequestParam url:String): String{

        val swaggerURL = URL(url.replace(" ","%20"))
        val swaggerText = swaggerURL.readText()
        val md5= hashString("MD5",swaggerText)

        val f =  File("${md5}.dot.svg")
        if (!f.isFile()) {

            val swaggerObject = SwaggerParser().parse(swaggerText)
            val codegen = PlantUMLCodegen(swaggerObject,  false, false)
            val puml = codegen.generatePuml()
            val puml2Dot = Puml2Dot(puml)
            val output = puml2Dot.dot()

            File("${md5}.dot").printWriter().use { out ->
                out.write(output)
            }

            val p = Runtime.getRuntime().exec("dot -Tsvg -O ${md5}.dot")
            p.waitFor()

        }

        var svg = File("${md5}.dot.svg").readText()
        return svg

    }

    @CrossOrigin
    @RequestMapping("/relations.svg")
    fun relations(@RequestParam url:String): String{


        val md5= hashString("MD5",url)

        val f =  File("${md5}.svg")
        if (!f.isFile()) {

            val relations = Relations(url)
            val map = relations.relationMap
            val nameMap = relations.nameLookup
            val output = RelationBuilder(relations.identifier, map, nameMap).dot()

            File("${md5}.dot").printWriter().use { out ->
                out.write(output)
            }

            val p = Runtime.getRuntime().exec(arrayOf("/bin/sh","-c","dot -Tsvg ${md5}.dot -Gsize=20,14\\! -Gdpi=100 | xsltproc --novalid AddLinks.xsl - > ${md5}.svg"))
            p.waitFor()

        }

        var svg = File("${md5}.svg").readText()
        return svg

    }


    @CrossOrigin
    @RequestMapping("/relations.png")
    fun relations_png(@RequestParam url:String): ByteArray{


        val md5= hashString("MD5",url)

        val f =  File("${md5}.png")
        if (!f.isFile()) {

            val relations = Relations(url)
            val map = relations.relationMap
            val nameMap = relations.nameLookup
            val output = RelationBuilder(relations.identifier, map, nameMap).dot()

            File("${md5}.dot").printWriter().use { out ->
                out.write(output)
            }

            val p = Runtime.getRuntime().exec(arrayOf("/bin/sh","-c","dot -Tpng ${md5}.dot -o$md5.png"))
            p.waitFor()

        }

        var png = File("${md5}.png").readBytes()
        return png

    }

*/
    private fun hashString(type: String, input: String) =
            MessageDigest
                    .getInstance(type)
                    .digest(input.toByteArray())
                    .map { String.format("%02X", it) }
                    .joinToString(separator = "")

}
