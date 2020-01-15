describe('The course overview page', function() {
  it('successfully loads', function() {
    cy.visit('/courses/all')
  })

  it('displays the website name', function() {
    cy.contains('MacraMicia')
  })

  it('displays the links to other pages', function() {
    cy.get('[data-cy=homeNav]')
    cy.get('[data-cy=coursesNav]')
    cy.get('[data-cy=aboutNav]')
    cy.get('[data-cy=loginNav]')
    cy.get('[data-cy=signUpNav]')
  })

  it('displays the subpage name', function() {
    cy.contains('Courses')
  })

  it('displays course details in cards', function() {
    cy
        .get('[data-cy=courseCard]')
        .should('be.visible')
        // .children().should('have.length', 2)

        // .get('[data-cy=tableCoursesHeaderTitle]')
        // .contains('Title')
        // .get('[data-cy=tableCoursesHeaderDescription]')
        // .contains('Description')
        // .get('[data-cy=tableCoursesHeaderDate]')
        // .contains('Date')
        // .get('[data-cy=tableCoursesHeaderVenue]')
        // .contains('Venue')
        // .get('[data-cy=tableCoursesHeaderSpots]')
        // .contains('Remaining Spots')
        //
        // .get('[data-cy=tableCoursesBody]')
        // .children().should('have.length.greaterThan', 0)
 })

})