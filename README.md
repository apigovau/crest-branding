
# Australian Government Crest Branding API

This api generates Government logos using the coat of arms that follow [the guidelines](https://beta.dta.gov.au/help-and-advice/guides-and-tools/requirements-australian-government-websites/branding)

Although this api is live, it's not monitored or setup to horizontally scale.

Use it in production at your risk.

You're better off using it to generate images and then serve them yourself

## Basic usage

There are two endpoints:

### Inline usage
https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png


### Stacked usage
https://api-gov-au-crest-branding.apps.y.cld.gov.au/stacked.png


## Params
Both endpoints take the same parameters:

### 'agency'
The name of the agecny to display.
You can add as many '&agency' paramaters as you like, they will be added in order.
The guidelines cater for sub-headings, for things such as function names.
These use a non-bold font.
If you prepend an 'agency' param with a pipe char (|, or %7A), that text will be a sub-heading

### 'height'
Sets the total height of the image.
The width is derrived based on the longest 'agency' parameter

### 'square'
Overrides the natural aspect-ratio of the images and pads it out to be a square
