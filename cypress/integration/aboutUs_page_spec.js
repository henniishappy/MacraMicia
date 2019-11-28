describe('The aboutUs page', function() {
  it('successfully loads', function() {
    cy.visit('/aboutUs')
  })

  it('displays the website name', function() {
    cy.contains('MacraMicia')
  })

  it('displays the links to other pages', function() {
    cy.contains('Home')
    cy.contains('About Us')
    cy.contains('Create Course')
    cy.contains('Courses')
  })

  it('displays the subpage name', function() {
    cy.contains('About Us')
  })

  it('displays the current date and time', function() {
    cy.contains('Current date and time: ')
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

  it('should load index.html when clicking the Home button', function() {
    cy.contains('Home').click()
    cy.url()
      .should('include', '/')
  })

    it('lets You visit the Courses page', function() {
    cy.contains('Courses').click()
    cy.url()
      .should('include', '/courses/all')
  })

    it('lets You visit the Create Course page', function() {
    cy.contains('Create Course').click()
    cy.url()
      .should('include', '/courses/add')
  })
})
