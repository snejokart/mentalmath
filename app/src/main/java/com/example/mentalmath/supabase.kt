import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import kotlinx.serialization.json.Json

object Supabase {
    val supabase = createSupabaseClient(
        "https://jduajweztkmktqxeeivo.supabase.co",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpkdWFqd2V6dGtta3RxeGVlaXZvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM5OTYzMzAsImV4cCI6MjA0OTU3MjMzMH0.t1XYVcMaX1cE_0sx38QNr_yR1qfmUOcMbBCJWE-p0J8"
    )

    {
        defaultSerializer = KotlinXSerializer(Json{ignoreUnknownKeys=true})
        install(Auth)
        install(Postgrest)
        install(Storage)
        install(Realtime)

    }
}