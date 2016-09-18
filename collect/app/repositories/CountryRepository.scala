package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.Country

@Singleton
class CountryRepository extends Repository {
  def find(id: Int) : Country = find(classOf[Country], id)
}
