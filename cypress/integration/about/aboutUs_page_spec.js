describe('The aboutUs page', function() {
  it('successfully loads', function() {
    cy.visit('/aboutus')
  })

  it('displays the website name', function() {
    cy.contains('Macra Micia')
  })

  it('displays the links to other pages', function() {
    cy.get('[data-cy=homeNav]')
    cy.get('[data-cy=coursesNav]')
    cy.get('[data-cy=aboutNav]')
    cy.get('[data-cy=loginNav]')
    cy.get('[data-cy=signUpNav]')
  })

  it('displays the subpage name', function() {
    cy.contains('About Us')
  })

  it('displays heading', function() {
    cy.contains('About Us')
  })

  it('should display two images showing the user persona of Lisa and Claudia', () => {
    cy.get('[alt=Lisa]')
      .should('be.visible')
      .and(($img) => {
        // "naturalWidth" and "naturalHeight" are set when the image loads
        expect($img[0].naturalWidth).to.be.greaterThan(0)
      })

    cy.get('[alt=Claudia]')
      .should('be.visible')
      .and(($img) => {
        // "naturalWidth" and "naturalHeight" are set when the image loads
        expect($img[0].naturalWidth).to.be.greaterThan(0)
      })
  })
})
