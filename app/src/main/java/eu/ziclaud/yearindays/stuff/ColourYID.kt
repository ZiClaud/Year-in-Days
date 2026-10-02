package eu.ziclaud.yearindays.stuff

class ColourYID {
    var id: Int
    var hexColour: String
    var hexColourBorder: String
    var name: String? = ""

    constructor(id: Int, hexColour: String, hexColourBorder: String, name: String?) {
        this.id = id
        this.hexColour = hexColour
        this.hexColourBorder = hexColourBorder
        this.name = name
    }

    constructor(id: Int, hexColour: String, hexColourBorder: String) {
        this.id = id
        this.hexColour = hexColour
        this.hexColourBorder = hexColourBorder
    }

    fun getHexColourAsInt(): Long {
        assert(hexColour.startsWith("0x"))
        return hexColour.substring(2).toLong(16)
    }

    fun getHexColourBorderAsInt(): Long {
        assert(hexColourBorder.startsWith("0x"))
        return hexColourBorder.substring(2).toLong(16)
    }

    override fun toString(): String {
        return "ColourYID(id=$id, hexColour='$hexColour', hexColourBorder='$hexColourBorder', name=$name)"
    }
}
