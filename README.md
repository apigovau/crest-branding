
# Australian Government Crest Branding API

This api generates Government logos using the coat of arms that follow [the guidelines](https://beta.dta.gov.au/help-and-advice/guides-and-tools/requirements-australian-government-websites/branding)

Although this api is live, it's not monitored or setup to horizontally scale.

Use it in production at your risk.

You're better off using it to generate images and then serve them yourself

## Examples

### Inline
```
https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png?agency=Department%20Of%20Finance&height=200
```

![Inline](https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png?agency=Department%20Of%20Finance&height=200)



### Stacked
```
https://api-gov-au-crest-branding.apps.y.cld.gov.au/stacked.png?agency=Department%20Of%20Finance&height=200
```

![Inline](https://api-gov-au-crest-branding.apps.y.cld.gov.au/stacked.png?agency=Department%20Of%20Finance&height=200)



### Inline with multiple agencies
```
https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png?agency=Department%20Of%20Health&agency=Department%20of%20Foreign%20Affairs%20and%20Trade&agency=Attorney-General%27s%20Department&height=200
```

![Inline with multiple agencies](https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png?agency=Department%20Of%20Health&agency=Department%20of%20Foreign%20Affairs%20and%20Trade&agency=Attorney-General%27s%20Department&height=200)

### Stacked with multiple agencies
```
https://api-gov-au-crest-branding.apps.y.cld.gov.au/stacked.png?agency=Department%20Of%20Health&agency=Department%20of%20Foreign%20Affairs%20and%20Trade&agency=Attorney-General%27s%20Department&height=200
```

![Inline with multiple agencies](https://api-gov-au-crest-branding.apps.y.cld.gov.au/stacked.png?agency=Department%20Of%20Health&agency=Department%20of%20Foreign%20Affairs%20and%20Trade&agency=Attorney-General%27s%20Department&height=200)



### Inline and tertiary (also with forced new line)
```
https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png?agency=Department%20of%20the%0APrime%20Minister%20and%20Cabinet&agency=%7CGovernment%20Branding%20Unit&height=200
```

![Inline and tertiary ](https://api-gov-au-crest-branding.apps.y.cld.gov.au/inline.png?agency=Department%20of%20the%0APrime%20Minister%20and%20Cabinet&agency=%7CGovernment%20Branding%20Unit&height=200)


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

Lines won't wrap.

If you want to break up a line, add a newline (\n, or %0A).

### 'height'
Sets the total height of the image.

The width is derrived based on the longest 'agency' parameter

### 'square'
Overrides the natural aspect-ratio of the images and pads it out to be a square
