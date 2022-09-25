val visitedPlaces = Map(
  "IL" -> "Wrigley Field",
  "MO" -> "St Louis Zoo",
  "NY" -> "Comedy Cellar",
  "CA" -> "Mission Delores Park",
  "FL" -> "Disney World"
)

val stateVisits = List("MO", "FL", "IL", "CA", "NY")

stateVisits.flatMap(state => visitedPlaces.get(state))
stateVisits.map(state => visitedPlaces.getOrElse(state, "Unknown"))
stateVisits.map(state => visitedPlaces.get(state))

val s = "string"
s.head
s.charAt(s.length - 1)
s.tail.dropRight(1)