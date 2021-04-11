<br />
<p align="center">
  <a href="https://github.com/mirorauhala/bloom-or-gloom">
    <img src="./.github/logo.jpg" alt="Logo">
  </a>

  <h3 align="center">Bloom or Gloom</h3>

  <p align="center">
    A Game about clicking your life together.
    <br />
    <a href="https://mirorauhala.github.io/bloom-or-gloom"><strong>Read the javadoc »</strong></a>
    <br />
    <br />
    <img src="https://github.com/mirorauhala/bloom-or-gloom/actions/workflows/gradle.yml/badge.svg">
    <img src="https://github.com/mirorauhala/bloom-or-gloom/actions/workflows/docs.yml/badge.svg">
  </p>
</p>
<br />
<br />

## Installing

```
git clone https://github.com/mirorauhala/bloom-or-gloom.git
```

### Building

Run the following commands in terminal:

```
gradlew clean
gradlew desktop:dist
gradlew :android:assembleDebug
```

Desktop `.jar` file can be found at `desktop/build/lib`.

Android `.apk` file can be found at `android/build/outputs/apk/debug`.
