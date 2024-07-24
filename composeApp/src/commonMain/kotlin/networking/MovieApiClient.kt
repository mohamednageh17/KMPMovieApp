package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import networking.model.MovieResponse
import utilis.Error
import utilis.NetworkError
import utilis.Result

object API {
    const val baseUrl = "https://api.themoviedb.org/3/movie"
    const val apiKey = "426bff346dfb35a5c824f8c3acddcc15"
    const val moviePoster = "https://image.tmdb.org/t/p/w500"
}

class MovieApiClient(
    private val httpClient: HttpClient
) {
    suspend fun fetchMovies(type: String = "popular"): Result<MovieResponse, Error> {
        val response = try {
            httpClient.get(
                urlString = "${API.baseUrl}/$type?api_key=${API.apiKey}"
            ) {

            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val body = response.body<MovieResponse>()
                Result.Success(body)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}