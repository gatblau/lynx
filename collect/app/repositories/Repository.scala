package repositories

import javax.inject.Inject

import play.db.jpa.JPAApi

class Repository {
  @Inject
  var db : JPAApi = _
}
