package com.geovannycode.plugins

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.*

fun Application.configureSwagger() {
    install(SwaggerUI) {
        swagger {
            swaggerUrl = "swagger"
            forwardRoot = false
        }
        info {
            title = "Ktor Student-Course REST API"
            version = "1.0.0"
            description = "Ktor Student-Course REST API"
            contact {
                name = "Geovanny Mendoza"
                url = "https://github.com/geovanny0401"
            }
        }
        server {
            url = environment.config.property("server.baseUrl").getString()
            description = "Ktor Student-Course REST API"
        }

        schemasInComponentSection = true
        examplesInComponentSection = true
        automaticTagGenerator = { url -> url.firstOrNull() }
    }
}