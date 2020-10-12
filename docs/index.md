## Summary

This will be an app focused on helping younger people (teens, college grads, and young professionals) who forget to text their friends back. This app will help forgetful users or those who struggle to manage inter-personal relationships, amidst the busyness of modern life. It will directly help users that struggle with anxiety to go beyond formal "text banter" when meeting a stranger and exchanging numbers, by prolonging conversations and interactions over a longer period of time in the hope of blossoming new friendships and deeper connections. It will also use the same set of features, to double as an app for forgetful family members, that similarly struggle to text or stay in touch with one another.

The app will accomplish intended functionality by relying on reading text message metadata from received and sent text messages. It will utilize the built in messages app in Android to send short **"KiTs"**, __keep in touch messages__, to friends and family to continue conversations for a period of months, rather than minutes.


## Intended users

* Young teens-- particularly,  for those who use social media and messaging platforms, but struggle with social-anxiety
> As a young teen, I want to text my friends back more often, so that  I can stay better connected and grow my relationships with them. I also don't want to feel FOMO, so I think this app will help me step past the "over-thinking" in my head and lead to more interactions digitally and eventually in-person.

* Millenials-- particularly,  for those who have just graduated college or live on their own and are working a busy job in a new city
> As a  millienial, I want to respond to my friends, acquaintances, and work connections in a timely manner to build my social network and create deeper and more meaningful connections with the individuals I've met throughout my life. This app will help me for the times I become too forgetful or busy.

* Parents-- particularly, for those who want to go above and beyond in parenting duties and realize that it's quite hard to manage a family  
> As a forgetful parent, I want to check in on my kids and parents more often to make sure they are okay and have everything they need. By using this app, I wont have to worry if I forget about them when work gets busy, because I'll be reminded to send a _**KiT**_.

* Grandparents-- particularly, for those hip grannies and gramps that are adventurous and like to do things on their own
> As a grandparent, I want to let my children and grandchildren know that I'm okay every so often. However, I can't handle these new-fangled devices. I want a basic app that helps me, so my family doesn't have to worry about me. Hopefully they'll also remember to call me back more often too.

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


## Persistent data

* Last messaged date
* Contact Info
* Incoming/ Outcoming text metadata
* Ignores/ Ignored contacts
* Generic KiT messages and auto-reply message templates

    
## Device/external services

Client component will need access to special services including: 

* contacts
* messaging
* text message metadata
* I'm considering hard-coding string options, if I can't find a good API.

This may work though: [Smart Reply Machine Learning Kit](https://developers.google.com/ml-kit/language/smart-reply/android),
technically this would be on-device, so it may not count as an external service.

<!--- * Google Gmail automessage API portion of gmail.
	* [GOOGLE AUTOMESSAGE API](https://www.googleapis.com/auth/gmail.compose)

I may scrape some greetings off [here](https://preply.com/en/blog/22-useful-english-greetings-for-every-day/#scroll-to-heading-18). --->


## Stretch goals/possible enhancements 

I would like to implement some of the stretch features of this app, on at least a _barebones_ level, for one social media platform. Hopefully, it will be for a commonly used one like FB messenger. At a barebones level in another app, I would just like to prompt a user to "KiT" message a friend on Facebook if a certain amount of time has passed since they last messaged each other, that's it.

* Other possible platform implementations
	* KiT implementation on Facebook Messenger
	* KiT implementation on WhatsApp
	

* Other potential features to be implemented on KiT:
	* KiT favorite contacts (Similar to T-mobile fave-five)
	* A feature that texts their friends who text them while they're away on vacation about when they'll get back to them. 
	* An ignore option to ignore messaging a particular person back, if they **ignore** a contact 3 times in a row, no more notifications will pop-up reminding the user to "KiT" the contact they ignored 3 times. This list of contacts can be overrode in the settings menu at a later time.
	* KiT motivational quotes selection in app.
	* Dog Theme for those who love their "woof"-ers

## Wireframe

### KiT Keep in Touch Android Application Wireframe:
![Wireframe diagram](img/wireframe.png)