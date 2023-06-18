# HighLow

A game where you guess if the next card will have a higher or lower rank than the current card.

This game is made in Java with LibGDX.

You can play the game on [Itch](https://dustyplant.itch.io/highlow)!

## Running the Game

This repo uses Gradle as the build tool. See the following instructions on how to run the game in each environment.

### Desktop

Unix based systems:

```bash
./gradlew run
```

Windows:

```bash
gradlew.bat run
```

### Html

```bash
# Build the distribution files (HTML, CSS, JavaScript)
./gradlew html:clean html:dist

# Start a local web server that hosts these static asset files
# This uses the built-in Python HTTP server on port 8000
python -m http.server --directory html/build/dist 8000

# Navigate to the HTTP server in your browser
# The following command works on MacOS
open http://localhost:8000
```

## Deployment

Any pushes to the `main` branch will trigger the CI/CD pipeline to build and deploy the latest version to Itch as an HTML build.
