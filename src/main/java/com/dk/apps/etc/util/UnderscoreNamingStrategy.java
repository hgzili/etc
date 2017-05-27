package com.dk.apps.etc.util;

import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.NamingStrategy;


/**
 * An improved naming strategy that prefers embedded underscores to mixed case
 * names
 * 
 * @author Terry Xu
 * @since 2014-09-14
 * @version $Revision: 1.0 $
 */
public class UnderscoreNamingStrategy implements NamingStrategy {
    private String tablePrefix = "";
    public static final UnderscoreNamingStrategy INSTANCE = new UnderscoreNamingStrategy();

    protected UnderscoreNamingStrategy() {
    }

    public String classToTableName(String className) {
        return tablePrefix + addUnderscores(StringHelper.unqualify(className)).toUpperCase();
    }

    public String propertyToColumnName(String propertyName) {
        return addUnderscores(StringHelper.unqualify(propertyName)).toUpperCase();
    }

    public String tableName(String tableName) {
        return tableName;
    }

    public String columnName(String columnName) {
        return columnName;
    }

    public String propertyToTableName(String className, String propertyName) {
        return classToTableName(className) + '_' + propertyToColumnName(propertyName).toUpperCase();
    }

    private String addUnderscores(String name) {
        StringBuffer buf = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if ('_' != buf.charAt(i - 1) && Character.isUpperCase(buf.charAt(i)) && !Character.isUpperCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }

    //getters and setters
    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

	@Override
	public String collectionTableName(String ownerEntity,
			String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logicalColumnName(String columnName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logicalCollectionTableName(String tableName,
			String ownerEntityTable, String associatedEntityTable,
			String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logicalCollectionColumnName(String columnName,
			String propertyName, String referencedColumn) {
		// TODO Auto-generated method stub
		return null;
	}

}