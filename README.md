# HackneyHome

This application is simply designed to be the front-facing connection for prospective customers to my company: The. H.T. Hackney Co.

## Portrait Mode
While in Portrait Mode, the app has three TextViews which act as buttons. When a user clicks on the TextView, it will reveal an ExpandingLayout. Each layout contains unique information from our core business model, to our locations and even a contact request form. 

![Picture](app/src/main/res/drawable/Portrait.gif)

## Landscape Mode
While in Landscape Mode, the app retains the same three TextViews, but instead of opening vertically, they open horizontally to display the same information. 

![Picture](app/src/main/res/drawable/Landscape.gif)

### Further Information
Upon installation the app will request the following permissions:
**Manifest.Permission.CALL_PHONE** (for placing calls via the phone links)
**Manifest.Permission.READ_SMS, READ_PHONE_STATE & READ_PHONE_NUMBERS** (for getting the phone's number to place in email intent)

This app allows users to submit an email request directly to the company and request a followup via Email, Phone or Text.


### Implemented Libraries
The ExpandingLayout : https://github.com/cachapa/ExpandableLayout

MaterialTextField : https://github.com/florent37/MaterialTextField
