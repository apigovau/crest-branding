package au.gov.dxa.graph

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.net.URL
import java.security.MessageDigest

import javax.imageio.*
import java.io.*

@RestController
class Controller {

    @CrossOrigin
    @RequestMapping("/stacked.png")
    fun stacked_png(@RequestParam lines:List<String>): ByteArray{


        val brand = Brand()
        val image = brand.stacked(lines)


        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()


    }

    private fun hashString(type: String, input: String) =
            MessageDigest
                    .getInstance(type)
                    .digest(input.toByteArray())
                    .map { String.format("%02X", it) }
                    .joinToString(separator = "")

}
