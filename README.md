# Media Organizer

Media Organizer is a lightweight Java Swing desktop application that helps you organize your media files (photos, videos, etc.) by their creation date. 
It lets you filter files by day, week, or month, and copies them into a new folder on your Desktop, organized into subfolders based on the selected time filter.

## Features


* **Organize by Creation Date:** Automatically sorts your media files into subfolders by the date they were created.
* **Filter by Time Range:** Choose to organize files by day, week, or month.
* **User-Friendly Interface:** Intuitive GUI for easy interaction and configuration.
* **Non-destructive Copying:** Files are safely copied — the original files remain untouched.
* **Batch Processing:** Handle multiple files and directories simultaneously to save time.
* **Cross-Platform:** Runs on any system with Java installed.


## Getting Started

### Requirements

* Java 21
* IntelliJ IDEA (or any Java-compatible IDE)

### Running the App

1) Clone the repository: `git clone https://github.com/ErenUfuktepe/media-organizer.git`
2) Open the project in IntelliJ IDEA. 
3) Run the Main class to launch the application.


## How It Works

1) Select a source folder containing your media files.
2) Choose a filter type: by day, week, or month.
3) The app creates a new folder on your Desktop, such as: `~/Desktop/media-organizer/`
4) Inside that folder, files are copied into subfolders based on their creation date.


## Tech Stack

* Java 21
* Java Swing (for the UI)
* Built using IntelliJ IDEA (no Maven or Gradle)