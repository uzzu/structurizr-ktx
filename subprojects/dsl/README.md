# Structurizr Kotlin DSL

This library contains an implementation of the Kotlin DSL for [structurizr/java](https://github.com/structurizr/java), and it can be used for Kotlin scripting, too.

[See the DSL examples](../../examples/dsl/script.main.kts)

## Setup

### For Gradle

```kotlin
dependencies {
    implementation("co.uzzu.structurizr.ktx:dsl:0.0.5")
}
```

### For Kotlin script

```kotlin
@file:DependsOn("com.structurizr:structurizr-core:1.5.0")
@file:DependsOn("co.uzzu.structurizr.ktx:dsl:0.0.5")

import co.uzzu.structurizr.ktx.dsl.*
import co.uzzu.structurizr.ktx.dsl.model.*
import co.uzzu.structurizr.ktx.dsl.view.*
import com.structurizr.model.*
import com.structurizr.view.*
```

If using in the kotlin script, recommend you to add settings to `.editorconfig` as following:

```editorconfig
[*.{kt, kts}]
max_line_length=256 # or more higher
disabled_rules=no-wildcard-imports
```

- The line length of Structurizr DSL tends to be long, so please increase max line length as you needed.
- This library contains very many Kotlin extensions, and import section in the script is expected to become too long, so recommend you to disable `no-wildcard-imports` rule.