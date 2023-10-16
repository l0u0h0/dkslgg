// Styled
import * as S from '@/styles/common/loading.style';

const LoadingComponent = ({ white }) => {
  return (
    <S.LoadingLayout $white={white}>
      <span className="loader" />
    </S.LoadingLayout>
  );
};

export default LoadingComponent;
