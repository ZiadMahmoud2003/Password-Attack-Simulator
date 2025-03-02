# Password Attack Simulator

## Overview
The **Password Attack Simulator** is a Java application designed to demonstrate two common password attack techniques: dictionary attacks and brute force attacks. This simulator allows users to input a username and then attempts to find the hardcoded password using the specified methods.

## Features
- **Username Input**: Users can enter a username for context during the attack.
- **Dictionary Attack**: The application first attempts to find the password using a list of common passwords from a dictionary file.
- **Brute Force Attack**: If the dictionary attack fails, the application will attempt to brute force the password using a defined character set.
- **User-Friendly GUI**: The application features a clean and organized graphical user interface, making it easy to use.

## Technologies Used
- Java Swing for the graphical user interface.
- BufferedReader for reading the dictionary file.
- Basic Java programming constructs for implementing the attack logic.

## Setup Instructions
1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/yourusername/PasswordAttackSimulator.git
   cd PasswordAttackSimulator
   ```

2. **Prepare the Dictionary File**:
   - Create a text file named `dictionary.txt` in the same directory as your Java application. Populate it with common passwords, each on a new line.

3. **Add the Logo**:
   - Download a logo image and save it as `logo.png` in the project directory (or update the code to point to the correct path).

4. **Compile and Run**:
   - Ensure you have Java installed on your machine. Compile and run the application using the following commands:
   ```bash
   javac PasswordAttackSimulator.java
   java PasswordAttackSimulator
   ```

## Usage Instructions
1. Open the application.
2. Enter a username in the provided text field.
3. Click the "Start Attack" button to initiate the attack process.
4. The application will first attempt the dictionary attack and, if unsuccessful, will proceed with the brute force attack.
5. Results will be displayed in the text area.

## Example Output
```
Username: user123
Starting dictionary attack...
Dictionary attack failed. Starting brute force attack...
Success: Password found through brute force.
