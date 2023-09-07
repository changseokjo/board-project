export default interface Board {
    boardNumber: number;
    title: string;
    contents: string;
    imageUrls: string[];
    writeDatetime: string;
    writeEmail: string;
    nickname: string;
    profileImageUrl: string | null;
}