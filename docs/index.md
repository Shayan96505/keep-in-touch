## Project Description

Keep in Touch is an app focused on helping young people who forget to respond to texts to **_keep in touch_**. Keep in Touch, also known as KiT, helps forgetful users and people that forget to nurture their relationships due to the busyness of modern life. KiT directly aids users that struggle with anxiety to make it past "texting small-talk" when exchanging numbers with a new connection by prolonging conversations and interactions, via seamless reminders to "keep in touch." If three days since the last message have passed, KiT nudges our users to text back. Over time, these continual reminders can blossom fresh friendships and unlikely connections, that may have otherwise died. KiT also doubles as an app for forgetful family members so that they can simply _keep in touch_ with one another.

KiT accomplishes this by using text message metadata from inbound and outbound messages and corresponding phone contacts. It utilizes the built in messages app in Android to send short  _keep in touch_ messages to friends and family.

### Key features

* Ignore contact 
    > Allows the user to not be reminded to message a contact back. After three confirmed ignores, the app will not nudge the user until the user overrides the ignore.
* Selection of user-type specified auto-replies
    > Allows users to gain access to specified auto-replies based on the user-type selected when they first download the app

## [Intended Users](work/intendedUsers.md)

## Functionality

* Upon download, users will select a user profile type, teenager, millenial, parent, grandparent which autofills the app with a
default profile and default _KiT_ messages based on their user profile type.
	* Some settings will include:
		* KiT reminder duration
		* KiT preset responses-- utilizing ML Kit's Smart Reply
		* different KiT themes-- Dark Theme, Light Theme
		* When/if the ML Kit's Smart Reply feature/ service doesn't work, this app will be able to function on its own
		with some preset, hard-coded messages courtesy of moi.
		
		

* User will power on phone and get notified within 5 minutes of boot-up with a list of contacts they haven't messaged back. These notifications will be based on **KiT** reminder duration, that is specified by their user profile data. Default will be to auto-prompt a reply if there hasn't been a text exchange in three days or greater.

* User will then be prompted either to send a quick, check-in message.  _Ex. " How are you, sorry I forgot to respond."_

* From there the user will be guided to the native android messenger where they can send additional responses and follow up about the previous day's messages.

* Additionally, there will be a check-mark option in the settings menu for anxious people, that auto-sends their friends a short message talking a little about how anxious they've been feeling and then following up, by asking the friend how they are doing. This method incorporates the mechanism of _"prolonged and repetitive exposure"_ to fears to eventually help anxious users move past their social-anxiety over time.
	* Example message: _"Hey, sorry for not responding. I've been feeling anxious. How have you been doing lately? I want to follow up on our last messages ..."_


## Device/external services

Client components will need access to special services including: 

* Contacts Native Android App
* Messaging Native Android App
* Text message metadata
* [Smart Reply Machine Learning Kit](https://developers.google.com/ml-kit/language/smart-reply/android)

## [Wireframe](work/wireframe.md)

## [Entity-Relationship Diagram](work/entityRelationshipDiagram.md)