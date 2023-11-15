package mvx.mvi.data

import com.justin.jetpacklearn.hilt.HiltRepository
import mvx.mvi.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object DataModule {

    @Provides
    fun providerRepository() : LoginRepository = LoginRepository()
}