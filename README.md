# MCBackpack API

**MCBackpack API** is a lightweight, asynchronous interface designed for high-performance Minecraft environments.  
It uses `CompletableFuture` to ensure non-blocking database operations for backpack management.

---

## 📦 Dependency

- **Group:** `io.github.mcvalac`  
- **Artifact:** `MCBackpackAPI`  
- **Version:** `2026.0.5-1` 

---

## 📥 Installation

### Maven

<details>
<summary><strong>Click to expand Maven setup</strong></summary>

#### 1. Add Repository
```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub Packages</name>
        <url>https://maven.pkg.github.com/MCValac/plugin-api</url>
    </repository>
</repositories>
```

#### 2. Add Dependency
```xml
<dependency>
  <groupId>io.github.mcvalac</groupId>
  <artifactId>MCBackpackAPI</artifactId>
  <version>2026.0.5-1</version>
</dependency>
```

#### 3. Credentials (settings.xml)
```xml
<servers>
    <server>
        <id>github</id>
        <username>${env.GITHUB_ACTOR}</username>
        <password>${env.GITHUB_TOKEN}</password>
    </server>
</servers>
```

</details>

---

### Gradle (Groovy)

<details>
<summary><strong>Click to expand Gradle Groovy setup</strong></summary>

#### 1. Repository
```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/MCValac/plugin-api")
        credentials {
            username = System.getenv('GITHUB_ACTOR') 
                ?: System.getenv('AGENT_GITHUB_NAME') 
                ?: System.getenv('USER_GITHUB_NAME')

            password = System.getenv('GITHUB_TOKEN') 
                ?: System.getenv('AGENT_GITHUB_TOKEN') 
                ?: System.getenv('USER_GITHUB_TOKEN')
        }
    }
}
```

#### 2. Add Dependency
```groovy
dependencies {
    implementation 'io.github.mcvalac:MCBackpackAPI:2026.0.5-1'
}
```

</details>

---

### Gradle (Kotlin DSL)

<details>
<summary><strong>Click to expand Gradle Kotlin setup</strong></summary>

#### 1. Repository
```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/MCValac/plugin-api")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
                ?: System.getenv("AGENT_GITHUB_NAME")
                ?: System.getenv("USER_GITHUB_NAME")

            password = System.getenv("GITHUB_TOKEN")
                ?: System.getenv("AGENT_GITHUB_TOKEN")
                ?: System.getenv("USER_GITHUB_TOKEN")
        }
    }
}
```

#### 2. Add Dependency
```kotlin
dependencies {
    implementation("io.github.mcvalac:MCBackpackAPI:2026.0.5-1")
}
```

</details>

---

## 🔐 Credentials Configuration

```text
CREDENTIALS_STRUCTURE:

USERNAME = System.getenv('GITHUB_ACTOR')
        ?: System.getenv('AGENT_GITHUB_NAME') 
        ?: System.getenv('USER_GITHUB_NAME')

PASSWORD = System.getenv('GITHUB_TOKEN')
        ?: System.getenv('AGENT_GITHUB_TOKEN') 
        ?: System.getenv('USER_GITHUB_TOKEN')
```

---

## ⚙️ How it Works

MCBackpack API is designed around **Asynchronous Non-Blocking Operations**. This means all interactions with the backpack storage (via the API or DB layer) run on separate threads and do not block the main server thread. This is crucial for maintaining server performance (TPS), especially on modern server software like FoliaMC.

### 1. Asynchronous Futures (`CompletableFuture`)
Methods in the `IMCBackpackProvider` and `IMCBackpackDB` interfaces return a `CompletableFuture`. A `CompletableFuture` represents a value that might not be available yet. 

Instead of waiting (blocking) for the storage to respond, you attach a callback (like `.thenAccept(...)` or `.thenApply(...)`) that will execute once the operation finishes.

```java
// Good (Asynchronous - Non-blocking):
provider.open(backpackUuid, playerUuid)
    .thenAccept(data -> {
        // This code runs LATER, when the data is retrieved.
        player.sendMessage("Backpack opened!");
    });
// The server continues ticking immediately without waiting.
```

### 2. Player Tracking
The API supports tracking which player is performing an operation (opening or saving). This is used for logging, analytics, and ensuring data integrity.

```java
// Open a backpack with player tracking
provider.open(backpackUuid, playerUuid);
```

### 3. Thread Safety
The internal implementation handles the underlying storage communication safely. It ensures that operations like saving inventory state are processed correctly, preventing race conditions or data loss even under heavy load.

---

## 📘 API Overview

<details>
<summary><strong>IMCBackpackProvider Interface</strong></summary>

### Key Methods

```java
CompletableFuture<BackpackData> open(String uuid);
CompletableFuture<BackpackData> open(String uuid, String playerUuid);
CompletableFuture<Void> save(String uuid, String contentBase64);
CompletableFuture<Void> save(String uuid, String contentBase64, String playerUuid);
CompletableFuture<Boolean> setPassword(String uuid, String password);
CompletableFuture<Boolean> verifyPassword(String uuid, String password);
```

</details>

---

## ⚡ Usage Examples

### Opening a Backpack
```java
IMCBackpackProvider provider = ...; // Get instance

provider.open(backpackUuid, playerUuid)
    .thenAccept(data -> {
        if (data != null) {
            // Handle backpack data
        }
    });
```

### Saving Backpack Data
```java
provider.save(backpackUuid, base64Content, playerUuid)
    .thenRun(() -> {
        System.out.println("Backpack saved successfully!");
    });
```
