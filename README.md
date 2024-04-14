tyUsing "typelevel/toolkit.local" `sbt` template (Toolkit to start building Typelevel apps), implemented super simple example:

```scala
//case class containing json fields (e.g. ip) we are interested in
case class AddressDetails(ip: String)

//manual json decoder: circe
implicit val ipAddressDecoder: io.circe.Decoder[AddressDetails] = (hCursor: HCursor) => 
  for {
    ip <- hCursor.downField("ip").as[String]
  } yield AddressDetails(ip)

//manual json decoder: http4s
implicit val ipDecoder: EntityDecoder[IO, AddressDetails] = jsonOf[IO, AddressDetails]

object Main extends IOApp.Simple:
  def run: IO[Unit] = {
    val apiUrl = "https://api.ipify.org/?format=json"
    EmberClientBuilder
      .default[IO]
      .build
      .use(client => 
        getAddressDetails(client, apiUrl)
      )
      .flatMap(addr => IO.println(addr.ip))
  }

//extract addressDetails from json response
def getAddressDetails(client: Client[IO], url: String): IO[AddressDetails] =
  client
    .expect[AddressDetails](url)
```
No parameterized functions (e.g. url to get, json decoder, http client to use, IO to return) => No tests :D

The example of Dockerizing is at https://github.com/dmgorsky/cl-kata-toolkit
