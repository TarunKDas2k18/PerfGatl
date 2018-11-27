package simulations

import baseConfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

/// this class also shows how to debug and print out session info
class CheckResponseViaObjects extends BaseSimulation {


  def getSingleEvent() :ChainBuilder = {
    repeat(times = 3) {

      exec(http("Get Event Reservation ")
        .get("event-reservations-service/Events/Search?lat=47.4797&lng=-122.2079&maxMeters=32187")
        .check(status.is(200)) // check for a specific status
        .check(jsonPath(path = "$.results[:1].name").saveAs(key = "name"))) // check for a specific status
        .exec { session => println(session); session } // this will give some high level data, but not that interesting as we don't have any variables!

    }
  }

  val scn = scenario("Event Reservation Service Search Event")

    .forever() {exec(getSingleEvent())
      .pause(5)
      //.pause(5)
    }

  // Run for a fixed duration ie 1 Minute below and not completing user journey
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(5),
     // rampUsers(300) during (60 second)
      //rampUsers(1) during  (20 second)
    ).protocols(httpConf.inferHtmlResources()))
    .maxDuration(1 minute)

}
