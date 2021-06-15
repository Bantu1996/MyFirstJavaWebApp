import static spark.Spark.*;

public class App {

    public static class HelloWorld {
        public static void main(String[] args) {
            staticFiles.location("/public");
            port(2020);
            get("/greet", (req, res) -> "Hello");
            post("/greet/:username", (req, res) -> {

                return "Hello " + req.queryParams("username");
            });



            get("/greet/:username", (request, response) -> {
                return "Hello: " + request.params(":username");
            });
            get("/greet/:username/language/:English", (request, response) -> {
                return "Hello " + request.params("username") + " you are greeted in " + request.params("English");
            });
            get("/greet/:username/language/:Xhosa", (request, response) -> {
                return "Molo " + request.params("username") + " ubulisiwe ngesi " + request.params("Xhosa");
            });
            get("/greet/:username/language/:Afrikaans", (request, response) -> {
                return "Halo " + request.params("username") + " jy is groet van " + request.params("Afrikaans");
            });
        }
    }
}
