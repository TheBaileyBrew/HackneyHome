**The Hackney Home Application**

This application is simply designed to be the front-facing connection for prospective customers to my company: The. H.T. Hackney Co.


While in Portrait Mode, the app has three TextViews which act as [Buttons]. When a user clicks on the TextView, it will reveal an ExpandingLayout. Each layout contains unique information from our core business model, to our locations and even a contact request form. 

![Picture](drawable/Portrait.gif)

While in Landscape Mode, the app retains the same three TextViews, but instead of opening vertically, they open horizontally to display the same information. 

![Picture](drawable/Landscape.gif)


Upon installation the app will request the following permissions:

**Manifest.Permission.CALL_PHONE** (for placing calls via the phone links)

**Manifest.Permission.READ_SMS, READ_PHONE_STATE & READ_PHONE_NUMBERS** (for getting the phone's number to place in email intent)


This app allows users to submit an email request directly to the company and request a followup via Email, Phone or Text.


**ExpandingLayout comes graciously through the following Library implementation**
https://github.com/cachapa/ExpandableLayout
