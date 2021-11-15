SELECT t1.username as UserName, COALESCE(t1.count, -1) as NoOfCreatedRoles, COALESCE
(t3.count, -1) as NoOfCreatedAndEnabledRoles, COALESCE(t2.count, -1) as NoOfUpdatedRoles
FROM
    (SELECT UPPER(TRIM(CreatedBy)) as username, COUNT(CreatedBy) as count
    FROM UserRole
    GROUP BY UPPER(TRIM(CreatedBy))
    ORDER By UPPER(TRIM(CreatedBy)) DESC) as t1
    LEFT JOIN
    (SELECT UPPER(TRIM(UpdatedBy)) as username, COUNT(UpdatedBy) as count
    FROM UserRole
    GROUP BY UPPER(TRIM(UpdatedBy))
    ORDER By UPPER(TRIM(UpdatedBy)) DESC) as t2 ON t1.username = t2.username
    LEFT JOIN
    (SELECT UPPER(TRIM(CreatedBy)) as username, COUNT(CreatedBy) as count
    FROM UserRole
    WHERE IsEnabled = TRUE
    GROUP BY UPPER(TRIM(CreatedBy))
    ORDER By UPPER(TRIM(CreatedBy)) DESC) as t3 ON t1.username = t3.username