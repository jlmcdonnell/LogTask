# LogTask

## Implementation Notes

- Task implemented as per requirements below with minimal emphasis on design / UX. 
- [Orbit MVI](https://github.com/orbit-mvi/orbit-mvi) used for ViewModel MVI implementation.
- Jetpack Compose could be used to simplify building of UI and to remove DataBinding. To follow the requirements I have not included Compose in this project.
- Tested on a Fairphone 4 device running Android 12, and an Android 10 emulator.

## Requirements

- [x] Functional requirements
    - Text input and pressing OK button to appends to log the list
    - Time the button was clicked (in UTC) used for log item
- [x] Text should also be written to a text file in the device
    - `LogItem`s are serialized to JSON and appended to a file in application data directory
    - `LogItem`s displayed when application loads or when the log store is updated
    - See `TextFileLogStore`
- [x] Use a layout.xml file to create the view.
    - XML used for all views
- [x] Use a view model to update the text with
timestamps.
    - See `LogViewModel`
- [x] Use Data Binding if possible
    - One-way data binding implemented for the list items, however I did not use many features of DataBinding due to the time constraint and my lack of knowledge in using it.
- [x] Unit tests
    - Unit tests written for the use cases, log file store, and time provider.
    - Full test coverage not attained due to time constraint    
- [x] Simple Espresso UI test
    - See `LogActivityTest`
