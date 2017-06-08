import { MyDisputePage } from './app.po';

describe('my-dispute App', () => {
  let page: MyDisputePage;

  beforeEach(() => {
    page = new MyDisputePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
