package au.gov.dxa.graph

import org.junit.Assert
import org.junit.Test

class GraphvizTest{
/*
    val puml = """
@startuml
hide empty members
set namespaceSeparator none

skinparam class {
	BackgroundColor PaleGreen
	ArrowColor RoyalBlue
	BorderColor DimGray
}

class  Pet {
	 - id <b>:Int64</b>
	 - name <b>:String</b>
	 - tag <b>:String</b>
}

class  NewPet {
	 - name <b>:String</b>
	 - tag <b>:String</b>
}

class  Error {
	 - code <b>:Int32</b>
	 - message <b>:String</b>
}


interface FindPetsApi {
	 + <i>findPets(String[] tags,Integer limit)</i><b>:Pet[]</b>  <b><color:red> throws Error </color> </b>
}

interface AddPetApi {
	 + <i>addPet(NewPet pet)</i><b>:Pet</b>  <b><color:red> throws Error </color> </b>
}

interface Find Pet By IdApi {
	 + <i>find pet by id(Integer id)</i><b>:Pet</b>  <b><color:red> throws Error </color> </b>
}

interface DeletePetApi {
	 + <i>deletePet(Integer id)</i><b>:void</b>  <b><color:red> throws Error </color> </b>
}


FindPetsApi -->    Pet
FindPetsApi -->    Error
AddPetApi -->    Pet
AddPetApi -->    NewPet
AddPetApi -->    Error
Find Pet By IdApi -->    Pet
Find Pet By IdApi -->    Error
DeletePetApi -->    Error


@enduml
"""


    @Test
    fun can_get_puml_from_swagger(){
        val swaggerText = GraphvizTest::class.java.getResource("/swagger.json").readText()
        Assert.assertNotNull(swaggerText)
        Assert.assertNotEquals("",swaggerText)

        val swaggerObject = SwaggerParser().parse(swaggerText)
        val codegen = PlantUMLCodegen(swaggerObject,  false, false)
        var puml = codegen.generatePuml()

        Assert.assertNotNull(puml)
        Assert.assertNotEquals("",puml)

        //println(puml)
    }


    @Test
    fun can_get_dot_from_puml(){
        val puml2Dot = Puml2Dot(puml)
        Assert.assertTrue(puml2Dot.classes.filter { it.name == "Pet" }.isNotEmpty())
        Assert.assertEquals(3,puml2Dot.classes.size)
        //println(puml2Dot.interfaces)
        //println(puml2Dot.dot())
    }
    */
}
