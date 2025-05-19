# pkg

An open source maven/npm/pypi package manager.

> Warning: This is a work in progress and not ready for production use.

## Use in your code

### Gradle

```kotlin
// With com.vanniktech.maven.publish plugin
mavenPublishing {
    publishing {
        repositories {
            maven {
                name = "guimauveDigital" // Replace with your repository name
                url = uri("https://pkg.guimauve.digital/maven2") // Replace with your repository URL
            }
        }
    }
}
```

Note: If you run this repository locally, you need to add `isAllowInsecureProtocol = true` to allow http.

### npm

Coming soon

### pypi

Coming soon

## Deploy your instance

Coming soon
