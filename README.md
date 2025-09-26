# Secret Diary Android App

An Android app for securely writing and saving diary entries with a simple login PIN feature. The app allows users to write, save, review, and undo their diary notes with timestamps, storing data locally using SharedPreferences.

---

## Features

- Secure login screen with PIN (default PIN: 1234).
- Add new diary entries with automatic timestamping.
- View all saved diary entries in reverse chronological order.
- Undo the last diary entry with confirmation dialog.
- Diary data persisted locally using SharedPreferences.
- Simple, intuitive user interface.

---

## Usage

- Upon launching, enter the PIN to authenticate.
- Write diary text in the input field and tap "Save" to add the entry with current timestamp.
- View all diary entries below in reverse chronological order, separated by timestamps.
- Tap "Undo" to remove the last saved diary entry (with confirmation).
- Diary entries are saved persistently and restored on app launch.

---

## Code Highlights

- Uses `kotlinx.datetime` to get and format the current local date-time for timestamps.
- Diary entries saved and loaded from `SharedPreferences` as a single string with entries separated by double newlines.
- The latest entries appear at the top by reversing the entry list.
- Implements a simple login using a PIN with basic validation.
- Confirmation dialog for undoing the last entry.

---
