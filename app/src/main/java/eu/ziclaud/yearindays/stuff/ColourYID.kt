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

    override fun toString(): String {
        return "ColourYID(id=$id, hexColour='$hexColour', hexColourBorder='$hexColourBorder', name=$name)"
    }
}
