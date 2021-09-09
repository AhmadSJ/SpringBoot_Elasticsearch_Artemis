package com.esartemisguide.esartemis.repositories;

import com.esartemisguide.esartemis.models.SpaceShip;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceShipRepository extends ElasticsearchRepository<SpaceShip, String> {
}
