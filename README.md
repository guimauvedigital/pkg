# pkg

An open source maven/npm/pypi package manager.

> Warning: This is a work in progress and not ready for production use.

## Use in your code

### Gradle

To download packages:

```kotlin
repositories {
    maven {
        url = uri("https://pkg.guimauve.digital/maven2") // Replace with your repository URL
    }
}
```

To publish packages:

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

In your `.npmrc`:

```ini
@organization-name:registry=https://pkg.guimauve.digital/npm/
```

### pypi

In your `pip.conf` (to download packages):

```ini
[global]
extra-index-url = https://pkg.guimauve.digital/pypi/simple
```

In your `~/.pypirc` (to publish packages):

```ini
[guimauve-digital]
repository = https://pkg.guimauve.digital/pypi/
```

## Deploy your instance

Coming soon
