/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.position;

import javax.time.InstantProvider;

import com.opengamma.DataNotFoundException;
import com.opengamma.engine.position.Portfolio;
import com.opengamma.engine.position.PortfolioNode;
import com.opengamma.engine.position.Position;
import com.opengamma.engine.position.PositionSource;
import com.opengamma.id.UniqueIdentifier;

/**
 * A position master that can be managed.
 * <p>
 * The position master provides a uniform structural view over a set of positions
 * holding them in a tree structure portfolio. This interface provides methods
 * that allow the master to be searched and updated.
 */
public interface ManageablePositionMaster extends PositionSource {

  /**
   * Checks if this position master manages the unique identifier.
   * @param uid  the unique identifier, not null
   * @return true if the master manages the identifier
   */
  boolean isManagerFor(UniqueIdentifier uid);

  /**
   * Checks if this position master allows modification of the underlying data source.
   * @return true if the master supports modification
   */
  boolean isModificationSupported();

  //-------------------------------------------------------------------------
  /**
   * Finds a specific portfolio by identifier.
   * @param uid  the unique identifier, not null
   * @return the portfolio, null if not found
   * @throws IllegalArgumentException if the identifier is not from this position master
   */
  @Override
  Portfolio getPortfolio(final UniqueIdentifier uid);

  /**
   * Finds a portfolio by unique identifier at an instant.
   * Any version in the unique identifier is ignored.
   * @param uid  the unique identifier, not null
   * @param instantProvider  the instant to query at, not null
   * @return the portfolio, null if not found
   * @throws IllegalArgumentException if the identifier is not from this position master
   */
  Portfolio getPortfolio(final UniqueIdentifier uid, final InstantProvider instantProvider);

  //-------------------------------------------------------------------------
  /**
   * Finds a specific portfolio node by identifier.
   * @param uid  the unique identifier, not null
   * @return the portfolio node, null if not found
   * @throws IllegalArgumentException if the identifier is not from this position master
   */
  @Override
  PortfolioNode getPortfolioNode(final UniqueIdentifier uid);

  /**
   * Finds a portfolio node by unique identifier at an instant.
   * Any version in the unique identifier is ignored.
   * @param uid  the unique identifier, not null
   * @param instantProvider  the instant to query at, not null
   * @return the portfolio node, null if not found
   * @throws IllegalArgumentException if the identifier is not from this position master
   */
  PortfolioNode getPortfolioNode(final UniqueIdentifier uid, final InstantProvider instantProvider);

  //-------------------------------------------------------------------------
  /**
   * Finds a specific position by identifier.
   * @param uid  the unique identifier, not null
   * @return the portfolio node, null if not found
   * @throws IllegalArgumentException if the identifier is not from this position master
   */
  @Override
  Position getPosition(final UniqueIdentifier uid);

  /**
   * Finds a position by unique identifier at an instant.
   * Any version in the unique identifier is ignored.
   * @param uid  the unique identifier, not null
   * @param instantProvider  the instant to query at, not null
   * @return the portfolio node, null if not found
   * @throws IllegalArgumentException if the identifier is not from this position master
   */
  Position getPosition(final UniqueIdentifier uid, final InstantProvider instantProvider);

  //-------------------------------------------------------------------------
  /**
   * Searches for portfolios matching the request.
   * 
   * @param request  the request to add, not null
   * @return the matched portfolios, not null
   * @throws IllegalArgumentException if the request is invalid
   */
  SearchPortfoliosResult searchPortfolios(final SearchPortfoliosRequest request);

  /**
   * Gets a managed portfolio.
   * 
   * @param portfolioUid  the unique identifier, not null
   * @return the portfolio, null if not found
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the portfolio is not found
   */
  ManagedPortfolio getManagedPortfolio(final UniqueIdentifier portfolioUid);

  /**
   * Adds a portfolio to the data store, including all nodes and positions.
   * <p>
   * This method will add the whole tree of nodes and positions if they are specified.
   * The portfolio may originate from another position master.
   * 
   * @param request  the request, not null
   * @return the new unique identifier of the portfolio, not null
   * @throws IllegalArgumentException if the request is invalid
   */
  UniqueIdentifier addPortfolio(final AddPortfolioRequest request);

  /**
   * Updates a portfolio.
   * <p>
   * This method does not affect the tree nodes or positions.
   * The portfolio unique identifier must be versioned and be the latest version.
   * 
   * @param request  the request, not null
   * @return the new unique identifier of the portfolio, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the portfolio is not found
   */
  UniqueIdentifier updatePortfolio(final UpdatePortfolioRequest request);

  /**
   * Removes a portfolio.
   * <p>
   * If the unique identifier contains a version it must be the latest version.
   * <p>
   * Where possible, implementations should retain the data in such a way that the
   * portfolio can be reinstated.
   * 
   * @param portfolioUid  the portfolio unique identifier to remove, not null
   * @return the new unique identifier of the portfolio, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the portfolio is not found
   */
  UniqueIdentifier removePortfolio(final UniqueIdentifier portfolioUid);

  /**
   * Reinstates a previously removed portfolio.
   * <p>
   * Any version in the unique identifier will be ignored.
   * 
   * @param portfolioUid  the portfolio unique identifier to reinstate, not null
   * @return the new unique identifier of the portfolio, null if unable to reinstate
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the portfolio is not found
   */
  UniqueIdentifier reinstatePortfolio(final UniqueIdentifier portfolioUid);

  //-------------------------------------------------------------------------
  /**
   * Gets a managed portfolio node.
   * 
   * @param nodeUid  the unique identifier, not null
   * @return the portfolio node, null if not found
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the node is not found
   */
  ManagedPortfolioNode getManagedPortfolioNode(final UniqueIdentifier nodeUid);

  /**
   * Adds a portfolio node to the specified node.
   * <p>
   * This method will add the single node specified in the request.
   * 
   * @param request  the request, not null
   * @return the unique identifier of the created node, not null
   * @throws IllegalArgumentException if the request is invalid
   */
  UniqueIdentifier addPortfolioNode(final AddPortfolioNodeRequest request);

  /**
   * Updates a portfolio node, without updating child nodes or positions.
   * <p>
   * This method does not affect any other node or position.
   * The portfolio node unique identifier must be versioned and be the latest version.
   * 
   * @param request  the request, not null
   * @return the new unique identifier of the node, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the node is not found
   */
  UniqueIdentifier updatePortfolioNode(final UpdatePortfolioNodeRequest request);

  /**
   * Removes a portfolio node.
   * <p>
   * If the unique identifier contains a version it must be the latest version.
   * <p>
   * Where possible, implementations should retain the data in such a way that the
   * positions can be reinstated.
   * 
   * @param nodeUid  the node unique identifier to remove, not null
   * @return the new unique identifier of the node, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the node is not found
   */
  UniqueIdentifier removePortfolioNode(final UniqueIdentifier nodeUid);

  /**
   * Reinstates a previously removed portfolio node.
   * <p>
   * Any version in the unique identifier will be ignored.
   * 
   * @param nodeUid  the node unique identifier to reinstate, not null
   * @return the new unique identifier of the node, null if unable to reinstate
   * @throws IllegalArgumentException if the request is invalid
   */
  UniqueIdentifier reinstatePortfolioNode(final UniqueIdentifier nodeUid);

  //-------------------------------------------------------------------------
  /**
   * Searches for positions matching the request.
   * 
   * @param request  the request to add, not null
   * @return the matched positions, not null
   * @throws IllegalArgumentException if the request is invalid
   */
  SearchPositionsResult searchPositions(final SearchPositionsRequest request);

  /**
   * Gets a managed position.
   * 
   * @param positionUid  the unique identifier, not null
   * @return the position, null if not found
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the position is not found
   */
  ManagedPosition getManagedPosition(final UniqueIdentifier positionUid);

  /**
   * Adds a position to the specified node.
   * <p>
   * This method will add the single position specified in the request.
   * 
   * @param request  the request, not null
   * @return the unique identifier of the created position, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the parent node is not found
   */
  UniqueIdentifier addPosition(final AddPositionRequest request);

  /**
   * Updates a position, including the security key.
   * <p>
   * The position specified must be the latest version.
   * 
   * @param request  the request, not null
   * @return the new unique identifier of the position, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the position is not found
   */
  UniqueIdentifier updatePosition(final UpdatePositionRequest request);

  /**
   * Removes a position.
   * <p>
   * If the unique identifier contains a version it must be the latest version.
   * <p>
   * Where possible, implementations should retain the data in such a way that the
   * positions can be reinstated.
   * 
   * @param positionUid  the position unique identifier to remove, not null
   * @return the new unique identifier of the position, not null
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the position is not found
   */
  UniqueIdentifier removePosition(final UniqueIdentifier positionUid);

  /**
   * Reinstates a previously removed position.
   * <p>
   * Any version in the unique identifier will be ignored.
   * 
   * @param positionUid  the position unique identifier to reinstate, not null
   * @return the new unique identifier of the position, null if unable to reinstate
   * @throws IllegalArgumentException if the request is invalid
   * @throws DataNotFoundException if the position is not found
   */
  UniqueIdentifier reinstatePosition(final UniqueIdentifier positionUid);

}
