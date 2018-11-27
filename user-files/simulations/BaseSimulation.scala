package baseConfig

import io.gatling.core.Predef._
import io.gatling.http.Predef._



class BaseSimulation extends Simulation {

  val httpConf = http
   // .baseUrl("https://api.tabletop-stage.tiamat-origin.cloud/dev/")
    .baseUrl("https://api.tabletop.tiamat-origin.cloud/")
    .header("Accept", "application/json")
  //  .proxy(Proxy("localhost", 8888).httpsPort(8888)) // uncomment this line if you want to run through a HTTP proxy such as Fiddler or Charles Proxy

  val httpProtocol = http
    .baseUrl("https ://fonts.googleapis.com")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())

}
