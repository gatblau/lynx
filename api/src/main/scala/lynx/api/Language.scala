package lynx.api

sealed trait Language { def code: String }

case object English extends Language { val code = "en" }
case object Spanish extends Language { val code = "es" }
case object French extends Language { val code = "fr" }
case object German extends Language { val code = "de" }
case object Italian extends Language { val code = "it" }
case object Chinese extends Language { val code = "zh" }
case object Hindi extends Language { val code = "hi" }
case object Arabic extends Language { val code = "ar" }
case object Bengali extends Language { val code = "bn" }
case object Russian extends Language { val code = "ru" }
case object Japanese extends Language { val code = "ja" }
case object Dutch extends Language { val code = "nl" }
case object Swedish extends Language { val code = "sv" }
case object Bulgarian extends Language { val code = "bg" }
case object Croatian extends Language { val code = "hr" }
case object Czech extends Language { val code = "cs" }
case object Danish extends Language { val code = "da" }
case object Estonian extends Language { val code = "et" }
case object Finnish extends Language { val code = "fi" }
case object Greek extends Language { val code = "el" }
case object Hungarian extends Language { val code = "hu" }
case object Irish extends Language { val code = "ga" }
case object Latvian extends Language { val code = "lv" }
case object Lithuanian extends Language { val code = "lt" }
case object Maltese extends Language { val code = "mt" }
case object Polish extends Language { val code = "pl" }
case object Romanian extends Language { val code = "ro" }
case object Slovak extends Language { val code = "sk" }
case object Slovenian extends Language { val code = "sl" }
case object Turkish extends Language { val code = "tr" }