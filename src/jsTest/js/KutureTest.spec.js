const { Kuture } = require('kutures').dev.nathanpb.kutures

const kuture = new Kuture((resolve) => resolve(1))
kuture.then(value => console.log('resolved', value))


// console.log(kuture.then)
// (async () => console.log(await kuture))()
