SELECT
	gd.`name` '大区',
	gx. NAME '小区',
	u. NAME '姓名',
	CASE u.sex
WHEN '0' THEN
	'女'
WHEN '1' THEN
	'男'
END AS '性别',
 u.phone '电话',
 u.email '邮件',
 u.remark '备注'
FROM
	grain_user u
LEFT JOIN grain_group g ON u.group_id = g.group_id
LEFT JOIN grain_group gx ON g.parent_id = gx.group_id
LEFT JOIN grain_group gd ON gx.parent_id = gd.group_id
WHERE
	g. CODE LIKE '0626%'
ORDER BY
	gd.`name`,
	gx. NAME