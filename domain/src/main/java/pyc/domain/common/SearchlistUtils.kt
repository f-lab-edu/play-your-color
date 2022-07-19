package pyc.domain.common

import java.util.regex.Pattern


fun String.containsIgnoreCase(compare: String): Boolean =
    Pattern.compile(Pattern.quote(compare), Pattern.CASE_INSENSITIVE).matcher(this).find()

