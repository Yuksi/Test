/* Топ-5 стран по количеству населения в столице */

SELECT * FROM country co 
    JOIN city ci ON (co.Capital = ci.ID) 
ORDER BY ci.population DESC LIMIT 5;

/* Суммарное кол-во людей, говорящих на английском языке в Европе */

SELECT SUM(l.Percentage * c.Population)
FROM countrylanguage l 
    JOIN country c ON (l.CountryCode = c.Code) 
WHERE l.Language='English' and c.Continent = 'Europe';

/* Список стран с двумя и более официальными языками, в которых количество
неофициальных языков как минимум вдвое больше, чем официальных */

SELECT * FROM country c 
JOIN (
    SELECT COUNT(*) s, l.CountryCode ccode 
    FROM countrylanguage l 
    WHERE l.IsOfficial = 'T' 
    GROUP BY CountryCode
    ) isof ON (c.Code = isof.ccode) 
JOIN (
    SELECT COUNT(*) s, l.CountryCode ccode 
    FROM countrylanguage l 
    WHERE l.IsOfficial = 'F' 
    GROUP BY CountryCode
    ) notof ON (isof.ccode = notof.ccode)
WHERE isof.s >= 2 AND notof.s >= isof.s*2;

