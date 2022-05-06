package io.jamshid.navoiyterminology.domain.use_cases

data class UseCases(
    var insertTerm: InsertTerm,
    var updateTerm: UpdateTerm,
    var allTerm: GetAllTerm,
    var getTermByStatus: GetTermByStatus
)
