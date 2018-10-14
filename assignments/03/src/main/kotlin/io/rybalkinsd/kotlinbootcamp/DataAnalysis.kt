package io.rybalkinsd.kotlinbootcamp

class RawProfile(val rawData: String?) {
    fun getList(): List<String?> = rawData?.filter { ch -> ch != '\n' }!!. split(',')
    fun getMap(): MutableMap<String?, String?> {
        var map: MutableMap<String?, String?> = getList().map { el -> el?.split('=', limit = 2) }. associate { it!![0] to it[1].toLowerCase() }. toMutableMap()
        map.getOrPut("firstName") { null }
        map.getOrPut("lastName") { null }
        if (map.containsKey("age")) {
            if (!(map["age"]!!.fold(true) { acc, el -> el.isDigit() and acc }))
                map["age"] = null
        } else
            map.plus(Pair("age", null))
        return map
    }
}
class FacebookProfile(id: Long = 0, firstName: String? = null, lastName: String? = null, age: Int? = null) : Profile(id = id, firstName = firstName, lastName = lastName, age = age, dataSource = DataSource.FACEBOOK)
class LinkedinProfile(id: Long = 0, firstName: String? = null, lastName: String? = null, age: Int? = null) : Profile(id = id, firstName = firstName, lastName = lastName, age = age, dataSource = DataSource.LINKEDIN)
class VkProfile(id: Long = 0, firstName: String? = null, lastName: String? = null, age: Int? = null) : Profile(id = id, firstName = firstName, lastName = lastName, age = age, dataSource = DataSource.VK)

enum class DataSource {
    FACEBOOK,
    VK,
    LINKEDIN
}

sealed class Profile(
    var id: Long,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var dataSource: DataSource
)
class evalProfileNew(id: Long, firstName: String? = null, lastName: String? = null, age: Int? = null, dataSource: DataSource) : Profile(id = id, firstName = firstName, lastName = lastName, age = age, dataSource = dataSource) {
    fun choiceSource() = when (dataSource) {
        DataSource.VK -> VkProfile(id = id, firstName = firstName, lastName = lastName, age = age)
        DataSource.FACEBOOK -> FacebookProfile(id = id, firstName = firstName, lastName = lastName, age = age)
        else -> LinkedinProfile(id = id, firstName = firstName, lastName = lastName, age = age)
    }
}

fun String.returnSocial(): DataSource = when (this.toLowerCase()) {
    "vk" -> DataSource.VK
    "facebook" -> DataSource.FACEBOOK
    else -> DataSource.LINKEDIN
}
fun equalProfiles(p1: Profile, p2: Profile) = (p1.firstName == p2.firstName) and (p1.lastName == p2.lastName) and (p1.age == p2.age)

val avgAge: Map<DataSource, Double> = parsData().groupBy { it.dataSource }
        .toMutableMap()
        .map { it }
        .associate { it.key to it.value.map { it.age }
                .filter { it != null }.map { it!!.toInt() }. average() }

val groupedProfiles: Map<Long, List<Profile>> = parsData().groupBy { it.id }

val rawProfiles = listOf(
    RawProfile("""
            firstName=alice,
            age=16,
            source=facebook
            """.trimIndent()
    ),
    RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
    ),
    RawProfile("""
            fisrtName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
    ),
    RawProfile("""
            firstName=bella,
            age=36,
            source=vk
            """.trimIndent()
    ),
    RawProfile("""
            firstName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
    ),

    RawProfile(
        """
            lastName=carol,
            source=vk,
            age=49
            """.trimIndent()
    ),
    RawProfile("""
            firstName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
    ),
    RawProfile("""
            lastName=kent,
            firstName=dent,
            age=88,
            source=facebook
            """.trimIndent()
    )
)
fun parsData(): MutableList<Profile> {
    val listParsData = rawProfiles.map { it.getMap() }
    var person: Profile
    var listProfiles: MutableList<Profile> = mutableListOf()
    for (i in listParsData.indices) {
        person = evalProfileNew(id = i.toLong(),
                dataSource = listParsData[i]["source"]!!.returnSocial(),
                firstName = listParsData[i]["firstName"],
                lastName = listParsData[i]["lastName"],
                age = listParsData[i]["age"]?.toInt()).choiceSource()
        var listId = listProfiles.map { elem -> (if (equalProfiles(person, elem)) elem.id else -1L) }.filter { it != -1L }
        if (!listId.isEmpty())
            person = evalProfileNew(id = listId[0], dataSource = person.dataSource, firstName = person.lastName, lastName = person.lastName, age = person.age).choiceSource()
        println(person)
        listProfiles.add(person)
    }
//    var listToAvg: MutableMap<DataSource, List<Profile>> = listProfiles.groupBy { it.dataSource }.toMutableMap()
    return listProfiles
}
fun main(args: Array<String>) {
    val listParsData = rawProfiles.map { it.getMap() }
    var person: Profile
    var listProfiles: MutableList<Profile> = mutableListOf()
    for (i in listParsData.indices) {
        person = evalProfileNew(id = i.toLong(),
                dataSource = listParsData[i]["source"]!!.returnSocial(),
                firstName = listParsData[i]["firstName"],
                lastName = listParsData[i]["lastName"],
                age = listParsData[i]["age"]?.toInt()).choiceSource()
        var listId = listProfiles.map { elem -> (if (equalProfiles(person, elem)) elem.id else -1L) }.filter { it != -1L }
        if (!listId.isEmpty())
            person = evalProfileNew(id = listId[0], dataSource = person.dataSource, firstName = person.lastName, lastName = person.lastName, age = person.age).choiceSource()
        println(person)
        listProfiles.add(person)
    }
}
//    avgAge = listToAvg.map { it }.associate { it.key to it.value.map { it.age }.filter { it != null }.map { it!!.toInt() }.average() }
//    groupedProfiles = listProfiles.groupBy { it.id }
