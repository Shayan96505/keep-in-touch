## Client component will need access to: 

* [Native Android Contacts App](https://developer.android.com/guide/components/intents-common#Contacts)

    > The native contacts app will be used to hold and relay contact information to KiT, so that contact name info will be associated with  device contacts outside of KiT. 
    
    > KiT relies on the native contact app  and contact info and will not function without it.
* [Native Android Messaging App](https://developer.android.com/guide/components/intents-common#Messaging)

    > The native text messaging app will be used to send outgoing texts to contacts, once the user has been and has agreed to responding either via smart-reply or a built-in option.
    
    > KiT relies on the native messaging app and will not function without it, because it doesn't have the ability to send and receive texts on its own.
* [Text message metadata](https://developer.android.com/reference/com/google/android/libraries/car/app/navigation/model/MessageInfo?hl=en)

    > The metadata will be used to take time-stamp info, as well as sent and received text info to determine which Contacts will be sent a KiT message.
    
    > KiT relies on text message metadata that can be accessed through.
    
* [Smart Reply Machine Learning Kit](https://developers.google.com/ml-kit/language/smart-reply/android)

    
    > It will just add context-related auto-replies based on the last message received form that contact in messages. 
    
    > KiT will be functional without access to Smart Reply machine learning kit.