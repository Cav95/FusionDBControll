package descriptionupdate.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import descriptionupdate.data.api.dao.DescriptionColumnName;
import descriptionupdate.data.api.dao.DescriptionDAO;
import descriptionupdate.data.queries.QueriesDescriptions;
import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.model.api.Description;

/**
 * Implementation of the DescriptionDAO interface for managing descriptions in
 * the database.
 */
public class DescriptionDAOImpl implements DescriptionDAO {

    private final Connection connection;

    /**
     * Constructor for PlanetDAOImpl.
     * 
     * @param connection the database connection.
     */
    public DescriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Description> getDescription(final String itaDescription, final String engDescription,
            final String group)
            throws DAOException {
        try (
                var statement = DAOUtils.prepare(connection, QueriesDescriptions.GET_ONE_DES, itaDescription,
                        engDescription,
                        group);
                var resultSet = statement.executeQuery();) {
            if (resultSet.next()) {
                var itaDes = resultSet.getString(DescriptionColumnName.ITA_DES.getColumnName());
                var engDes = resultSet.getString(DescriptionColumnName.ENG_DES.getColumnName());
                var groupRes = resultSet.getString(DescriptionColumnName.GROUP.getColumnName());
                var description = new Description(itaDes, engDes, groupRes);
                return Optional.of(description);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Description> getListDescription(final String itaDescription, final String engDescription,
            final String group) {
        List<Description> descriptions = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesDescriptions.GET_ALL_TABLE, itaDescription,
                        engDescription,
                        group);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                descriptions.add(new Description(resultSet.getString(DescriptionColumnName.ITA_DES.getColumnName()),
                        resultSet.getString(DescriptionColumnName.ENG_DES.getColumnName()),
                        resultSet.getString(DescriptionColumnName.GROUP.getColumnName())));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return descriptions;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDescription(final String itaDescription, final String engDescription, final String group) {
        {
            try (
                    var statement = DAOUtils.prepare(connection, QueriesDescriptions.INSERT_ONE_DES, itaDescription,
                            engDescription,
                            group);) {
                statement.executeUpdate();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteDescription(final String itaDescription, final String engDescription, final String group) {

        try (
                var statement = DAOUtils.prepare(connection, QueriesDescriptions.DELETE_ONE_DES, itaDescription,
                        engDescription,
                        group);) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDescription(final String exItaDescription, final String exEngDescription, final String exGroup,
            String newItaDescription, String newEngDescription) {
        try (
                var statement = DAOUtils.prepare(connection, QueriesDescriptions.UPDATE_ONE_DES,
                        newItaDescription, newEngDescription, exItaDescription, exEngDescription, exGroup);) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllGroupTypeString() throws DAOException {
        List<String> groupTypes = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesDescriptions.ALL_GROUP_TYPE_STRING);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                groupTypes.add(resultSet.getString(DescriptionColumnName.GROUP.getColumnName()));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return groupTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Description> getSimilarItalianDescriptions(String itaDescription, String engDescription, String group)
            throws DAOException {
        List<Description> descriptions = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesDescriptions.SIMILAR_ITA_DES, itaDescription,
                        engDescription, group);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                descriptions.add(new Description(resultSet.getString(DescriptionColumnName.ITA_DES.getColumnName()),
                        resultSet.getString(DescriptionColumnName.ENG_DES.getColumnName()),
                        resultSet.getString(DescriptionColumnName.GROUP.getColumnName())));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return descriptions;
    }
}
