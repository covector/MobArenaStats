SELECT
  class,
  COUNT(class) as c
FROM player_sessions
WHERE player_name = :player_name
GROUP BY class
ORDER BY c DESC;