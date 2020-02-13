package demo

//Command interface
interface Command {
    fun execute()
}

/**
 * Receiver
 */

//Receiver
class RobotRiddare(val namn: String) {

    private var arSvardetFramme: Boolean = false

    fun `ta fram svärdet`() {
        arSvardetFramme = true
        println("$namn tar fram svärdet")
    }

    fun `stoppa svärdet i skidan`() {
        arSvardetFramme = false
        println("$namn stoppar undan svärdet")
    }

    fun `slå med svärdet`() {
        if (arSvardetFramme)
            println("Riddaren $namn viftar aggressivt med sin stålpinne")
        else
            println("Riddaren $namn viftar vilt med en tom hand")
    }

    fun `ta hand om hästen`() {
        println("Snel hest får havre")
    }

    fun `ta hand om lacken`() {
        println("Säg till Lacken att han ser ut som en häst")
    }
}

/**
 * Concrete commands
 */

//Concrete command
class TaFramSvardet( private val robotRiddare: RobotRiddare ): Command {
    override fun execute() {
        robotRiddare.`ta fram svärdet`()
    }
}
//concrete command
class HuggMedSvardet( private val robotRiddare: RobotRiddare ): Command {
    override fun execute() {
        robotRiddare.`slå med svärdet`()
    }
}
//concrete command
class LaggUndanSvardet( private val robotRiddare: RobotRiddare): Command {
    override fun execute() {
        robotRiddare.`stoppa svärdet i skidan`()
    }
}
//concrete command
class `Ta hand om hästarna`(private val robotRiddare: RobotRiddare): Command {
    override fun execute() {
        robotRiddare.`ta hand om hästen`()
        robotRiddare.`ta hand om lacken`()
    }
}

/**
Invokers
 */

//Invoker
class Knapp( var command: Command) {
    fun klick() {
        command.execute()
    }
}

//Invoker
class Multifunktionsknapp( var commands: List<Command> ) {
    fun klick() {
        commands.forEach {
            it.execute()
        }
    }
}


fun main() {

    val bertil = RobotRiddare("Bertil")

    val knappHugg = Knapp( HuggMedSvardet(bertil) )
    val knappTaFramSvardet = Knapp( TaFramSvardet(bertil) )
    val knappLaggUndanSvardet = Knapp( LaggUndanSvardet(bertil) )

    //Klick på knappar i "klienten"
    knappHugg.klick()
    knappTaFramSvardet.klick()
    knappHugg.klick()
    knappLaggUndanSvardet.klick()

/*
    println("###################")
    //Men varför inte en knapp som gör allt det här?
    val attackSekvens = listOf(TaFramSvardet(bertil),
        HuggMedSvardet(bertil), LaggUndanSvardet(bertil),
        `Ta hand om hästarna`(bertil)
    )
    //Klicka på multiknappen
    Multifunktionsknapp(attackSekvens).klick()
*/



}