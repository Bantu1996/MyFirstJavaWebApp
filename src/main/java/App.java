import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

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
                return "Hello " + request.params("username");
            });



            get("/hello", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "hello.handlebars");
            }, new HandlebarsTemplateEngine());


            post("/hello", (req, res) -> {
                String message = new String();
                Map<String, Object> map = new HashMap<>();
                // create the greeting message
                String greeting = req.queryParams("username");
                String language =  req.queryParams("radioBtn");
                if (language.equals("English")){
                    message = "Hello, " + greeting;
                }
                else if (language.equals("IsiXhosa")){
                    message = "Molo, " + greeting;
                }
                else {
                    message = "Goeie dae, " + greeting;
                }

                // put it in the map which is passed to the template - the value will be merged into the template
                map.put("greeting", message);
                return new ModelAndView(map, "hello.handlebars");
            }, new HandlebarsTemplateEngine());



            get("/greet/:username/language/:English", (request, response) -> {
                return "Hello " + request.params("username") + " you are greeted in " + request.params("English");
            });
            get("/greet/:username/language/:Xhosa", (request, response) -> {
                return "Molo " + request.params("username") + " ubulisiwe ngesi " + request.params("Xhosa");
            });
            get("/greet/:username/language/:Afrikaans", (request, response) -> {
                return "Goeie Dae " + request.params("username") + " jy is groet van " + request.params("Afrikaans");
            });
        }
    }
}
